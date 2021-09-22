pipeline {
  agent any
  stages {
    stage('check java') {
      steps {
        sh "echo $GIT_COMMIT"
        sh "echo $BUILD_NUMBER"
        sh 'java -version'
      }
    }

    stage('cleanup') {
        steps {
            sh './mvnw clean'
        }
    }

    stage('unit tests') {
      steps {
        sh './mvnw test'
        junit '**/target/surefire-reports/TEST-*.xml'
      }
    }

    stage('build') {
      steps {
        sh './mvnw -DskipTests package'
        archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
      }
    }

    stage('build image') {
      steps {
        sh 'docker build -t demo:latest -t demo:$BUILD_NUMBER .'
      }
    }

    stage('push image') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'tieto1harbor', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh "docker login -u '$USERNAME' -p $PASSWORD harbor.svc.tieto1.1-4.fi.teco.online"
                        sh "docker tag demo:$BUILD_NUMBER harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest"
                        sh "docker tag demo:$BUILD_NUMBER harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:$BUILD_NUMBER"
                        sh "docker push harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest"
                        sh "docker push harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:$BUILD_NUMBER"
            }
        }
    }

    stage('update cd repo') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                sh "rm -rf demo-cd"
                sh "git clone https://github.com/tecong/demo-cd.git"
                sh "cd demo-cd"
                sh "sed -i 's/tag:.*/tag: $BUILD_NUMBER/g' demo-helm/values.yaml"
                sh "git add demo-helm/values.yaml"
                sh "git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/tecong/demo-cd.git -m 'Tag updated to $BUILD_NUMBER'"
            }
        }
    }

  }
}