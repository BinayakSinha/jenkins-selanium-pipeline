pipeline {
    agent any
    
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
                // This will compile the code and run the Cucumber runners
                bat 'mvn clean test'
            }
        }
    }
    
    // The 'post' block ensures reports are processed whether the tests pass or fail
    post {
        always {
            echo 'Generating interactive Cucumber reports...'
            // This command is unlocked by the Cucumber plugin you installed
            cucumber 'target/*.json' 
        }
    }
}