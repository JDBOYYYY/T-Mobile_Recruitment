pipeline {
    agent any

    tools {
        maven 'Maven' // This should match the name you have configured for Maven
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Running Maven build command
                    sh 'mvn clean install'
                }
            }
        }
    }
}
