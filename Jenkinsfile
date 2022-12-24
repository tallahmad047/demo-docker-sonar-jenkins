pipeline{
   agent any
   tools{
       maven '3.10.1'
   }
   stages{
      stage("source"){
          steps{
             git branch: 'master' ,url:'https://github.com/tallahmad047/demo-docker-sonar-jenkins.git'
        }
      }
      stage ('build'){
        steps{
          sh 'mvn clean package'
        }
      }
   }
}