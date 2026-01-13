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
                sh '''
                docker run --rm \
                  -v "$WORKSPACE/backend:/app" \
                  -w /app/demo \
                  maven:3.9.9-eclipse-temurin-17 \
                  mvn clean package -DskipTests
                '''
            }
        }
    }
}
