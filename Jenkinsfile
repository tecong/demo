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
        withCredentials(bindings: [usernamePassword(credentialsId: 'tieto1harbor', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
          sh "docker login -u '$USERNAME' -p $PASSWORD harbor.svc.tieto1.1-4.fi.teco.online"
          sh "docker tag demo:$BUILD_NUMBER harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest"
          sh "docker tag demo:$BUILD_NUMBER harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:$BUILD_NUMBER"
          sh 'docker push harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:latest'
          sh "docker push harbor.svc.tieto1.1-4.fi.teco.online/demo/demo:$BUILD_NUMBER"
        }

      }
    }

    stage('call cd update') {
      steps {
        sh 'curl -k http://10.195.220.2:8080/job/demo-cd/buildWithParameters?token=testitoken&REMOTE_BUILD_NUMBER=$BUILD_NUMBER'
      }
    }

  }
}