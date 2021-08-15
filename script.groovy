def buildApp() {
  echo 'building the app...'
  sh 'npm install'
  sh 'npm run build' 
}

def buildImage() {
  echo 'building the docker image...'

  withDockerServer([uri:'tcp://docker:2376']){
    withDockerRegistry([credentialsId: 'nexus-docker-repo', url: 'https://192.168.1.77:8083']) { 
      def myImage = docker.build("192.168.1.77:8083/demo-app:${params.VERSION}")
      myImage.push()
    }
  }
}

def testApp() {
  echo 'testing the app...'
}

def deployApp() {
  echo 'deploying the app...'
  echo "version ${params.VERSION}"
}

return this
