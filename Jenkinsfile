pipeline {
    agent any

    environment {
        MAVEN_HOME = "${WORKSPACE}/maven"
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Install Maven') {
            steps {
                script {
                    def mavenUrl = "https://dlcdn.apache.org/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.tar.gz"
                    def mavenHome = "${env.MAVEN_HOME}"

                    sh '''
                       # Define the Maven download URL
                       MAVEN_URL=https://dlcdn.apache.org/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.tar.gz

                       # Create a directory for Maven installation
                       mkdir -p $MAVEN_HOME

                       # Download and extract Maven
                       curl -L -o maven.tar.gz $MAVEN_URL

                       # Extract Maven
                       tar -xzf maven.tar.gz -C $MAVEN_HOME --strip-components=1

                       # Clean up
                       rm maven.tar.gz
                    '''
                }
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/JDBOYYYY/T-Mobile_Recruitment.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
