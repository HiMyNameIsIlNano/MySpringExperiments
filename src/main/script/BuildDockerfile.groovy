String template = new File("${project.basedir}/src/main/docker/DockerfileTemplate".toString()).getText()

def dockerFileText = new groovy.text.SimpleTemplateEngine()
        .createTemplate(template)
        .make([fileName: project.build.finalName,
               volumeDir: "tmp"
])

println "Creating directory " + "${project.basedir}/target/dockerfile"
new File("${project.basedir}/target/dockerfile".toString()).mkdirs()

println "Writing to Dockerfile under " + "${project.basedir}/target/dockerfile"
File dockerFile = new File("${project.basedir}/target/dockerfile/Dockerfile".toString())

dockerFile.withWriter('UTF-8') {
    writer -> writer.write(dockerFileText)
}