def call() {

    def imageName = "node-app"

    stage('Checkout') {
        checkout scm
    }

    stage('Install Dependencies') {
        sh 'npm install'
    }

    stage('Build Docker Image') {
        sh "docker build -t  ${imageName} ."
    }

    stage('Deploy') {
        sh """
            docker compose down || true
            docker compose up -d --build
        """
    }

  
}