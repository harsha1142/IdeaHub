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
                echo 'Building backend'
                dir('backend/demo') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo 'Building frontend'
                dir('frontend') {
                    sh 'node -v'
                    sh 'npm -v'
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
    }

    post {
        success {
            echo 'Backend + Frontend build successful'
        }
        failure {
            echo 'Build failed'
        }
    }
}
