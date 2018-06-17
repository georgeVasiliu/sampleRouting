pipeline {
  agent any
  stages {
    stage('Build app') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Tests & report') {
      steps {
        sh 'mvn test'
        junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true)
      }
    }
  }
}