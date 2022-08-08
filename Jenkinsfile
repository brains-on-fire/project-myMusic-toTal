pipeline {
    agent any

    tools{
    	maven 'maven_home'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
			echo "Test"
			sh 'mvn test'
            }
        }
    }

}