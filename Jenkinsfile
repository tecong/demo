pipeline {
  agent any
  stages {
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

    stage('build image') {
      steps {
        sh 'docker build .'
      }
    }

  }
}