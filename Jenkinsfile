pipeline {
    agent any

    environment {
        MAVEN_HOME = "${WORKSPACE}/maven"
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Install Maven') {
            steps {
                sh '''
                   # Define the Maven version and download URL
                   MAVEN_VERSION=3.8.4
                   MAVEN_URL=https://downloads.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz

                   # Create a directory for Maven installation
                   mkdir -p $MAVEN_HOME

                   # Download and extract Maven
                   curl -o maven.tar.gz $MAVEN_URL
                   tar -xzf maven.tar.gz -C $MAVEN_HOME --strip-components=1

                   # Clean up
                   rm maven.tar.gz
                '''
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
