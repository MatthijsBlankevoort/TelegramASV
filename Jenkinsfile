pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
           steps {
               sh './gradlew test'
           }
       }
    }
}