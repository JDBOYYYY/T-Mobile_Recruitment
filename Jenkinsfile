pipeline {
    agent any

    tools {
        jdk 'jdk-21' // Ensure this matches the name configured in Jenkins
        maven 'Maven' // Ensure this matches the name configured in Jenkins
    }

    environment {
        JAVA_HOME = tool name: 'jdk-21', type: 'jdk'
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    // Check Java version
                    sh 'echo "JAVA_HOME=$JAVA_HOME"'
                    sh 'java -version'
                    sh 'javac -version'

                    // Check Maven version
                    sh 'mvn -version'

                    // Check Chrome version
                    sh 'google-chrome --version'

                    // Check ChromeDriver version
                    sh 'chromedriver --version'

                    // Check Selenium version
                    sh 'mvn dependency:tree | grep selenium'
                }
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    // Clean and install the project
                    sh 'mvn clean install'

                    // Run tests
                    sh 'mvn test'
                }
            }
        }
    }
}
