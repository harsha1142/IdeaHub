pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
        nodejs 'node18'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/harsha1142/IdeaHub.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend/demo') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend/ideahub') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                echo 'Building Docker images'
                sh 'docker build -t ideahub-backend:latest ./backend/demo'
                sh 'docker build -t ideahub-frontend:latest ./frontend/ideahub'
            }
        }

        stage('Deploy Application') {
            steps {
                echo '🚀 Deploying application'
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully'
        }
        failure {
            echo 'CI/CD pipeline failed'
        }
    }
}
