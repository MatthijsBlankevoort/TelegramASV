pipeline {
    agent { docker { image 'gradle:latest' } }
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