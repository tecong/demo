#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }
    stage('check java') {
        sh "java -version"
    }
    stage('clean') {
        sh "chmod +x mvnw"
        sh "./mvnw clean"
    }
    stage('unit tests') {
        try {
            sh "./mvnw test"
        } catch (err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }
    stage('build') {
        sh "./mvnw -DskipTests package"
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}