pipeline {
  agent any
  stages {
    stage('Build app') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Test results display') {
      steps {
        junit 'target/surefire-reports/*.xml'
      }
    }
  }
}