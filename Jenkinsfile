pipeline {
  agent any
  stages {
    stage('check java') {
      steps {
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
        sh 'docker build -t demo:latest .'
      }
    }

    stage('push image') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'tieto1harbor', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh "docker login -u '$USERNAME' -p $PASSWORD harbor.svc.tieto1.1-4.fi.teco.online"
                        sh "docker tag demo:latest harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest"
                        sh "docker push harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest"
            }
        }
    }

  }
}