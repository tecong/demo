pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        checkout scm
      }
    }

    stage('check java') {
      steps {
        sh 'java -version'
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

  }
}
