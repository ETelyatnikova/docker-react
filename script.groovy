def buildApp() {
  echo 'building the app...'
  sh 'npm install'
  sh 'npm run build' 
}

def buildImage() {
  echo 'building the docker image...'
  
  def myImage = docker.build("192.168.1.77:8083/demo-app:${params.VERSION}")
  withDockerRegistry(credentialsId: 'nexus-docker-repo', url: '192.168.1.77:8083') { 
    myImage.push()
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
