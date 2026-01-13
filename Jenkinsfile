pipeline {
    agent any

    stages {

        stage('Build Backend (Maven)') {
            steps {
                sh '''
                  docker run --rm \
                    -v "$PWD":/workspace \
                    -w /workspace/backend/demo \
                    maven:3.9.9-eclipse-temurin-17 \
                    mvn clean package -DskipTests
                '''
            }
        }

    }
}
