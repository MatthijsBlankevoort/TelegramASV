pipeline {
    agent { docker { image 'gradle:5.4.1' } }
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