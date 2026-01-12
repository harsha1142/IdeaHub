pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/harsha1142/IdeaHub'
            }
        }

        stage('Build Info') {
            steps {
                echo 'Code pulled successfully'
                sh 'echo Building project...'
            }
        }
    }
}
