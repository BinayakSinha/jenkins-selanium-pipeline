pipeline {
    agent any
    
    // Explicitly define JAVA_HOME so Jenkins doesn't guess
    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-26.0.1'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling Selenium/Cucumber project from GitHub...'
                checkout scm
            }
        }
        
        stage('Compile & Execute UI Tests') {
            steps {
                echo 'Running Maven Test Lifecycle...'
                bat 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            echo 'Generating interactive Cucumber reports...'
            cucumber 'target/cucumber.json' 
        }
    }
}