pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'gradle --version'
            }
        }
        stage('Test') {
           steps {
               sh './gradlew check'
           }
       }
    }
}