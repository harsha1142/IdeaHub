pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/harsha1142/IdeaHub'
            }
        }

        stage('Build Backend (Maven)') {
            steps {
                dir('backend') {
                    sh '''
                    docker run --rm \
                      -v "$PWD":/app \
                      -w /app \
                      maven:3.9.9-eclipse-temurin-17 \
                      mvn clean package -DskipTests
                    '''
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
    }
}
