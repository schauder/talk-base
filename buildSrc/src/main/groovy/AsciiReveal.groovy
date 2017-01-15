package de.schauderhaft.ascii.reveal

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class AsciiReveal implements Plugin<Project> {
    void apply(Project project) {

        project.plugins.with {
            apply com.github.jrubygradle.JRubyPlugin
            apply org.asciidoctor.gradle.AsciidoctorPlugin
        }
        
        project.dependencies.with {
            gems 'rubygems:slim:3.0.6'
            gems 'rubygems:thread_safe:0.3.5'
            gems 'rubygems:asciidoctor-diagram:1.4.0'
        }

        project.task('setupTools', type: Copy) {
            def url = this.getClass().getResource("/download.zip")
            def zipFile = new File(url.toURI())
            def outputDir = project.file("${project.buildDir}/download")
            
            from project.zipTree(zipFile)
            into outputDir
        }
        
        project.extensions.create("asciiReveal", AsciiRevealExtension)
        
        project.task('asciireveal') {
            doLast {
                println project.asciiReveal.srcDir
                println project.asciiReveal.srcFile
                println project.asciiReveal.template
            }
        }
    }
}

class AsciiRevealExtension {
    def String srcDir = "src/docs/asciidoc"
    def String srcFile = "index.asciidoc"
    def template = "black"
}

