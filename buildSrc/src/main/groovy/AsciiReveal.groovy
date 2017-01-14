package de.schauderhaft.ascii.reveal

import org.gradle.api.Plugin
import org.gradle.api.Project

class AsciiReveal implements Plugin<Project> {
    void apply(Project project) {

        project.plugins.with {
            apply com.github.jrubygradle.JRubyPlugin
//            apply org.ysb33r.Vfs
//            apply org.asciidoctor.Convert
//            apply name.mazgalov.vaadin.sass.Compiler
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

