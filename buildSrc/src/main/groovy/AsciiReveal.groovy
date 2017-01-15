package de.schauderhaft.ascii.reveal

import org.gradle.api.Plugin
import org.gradle.api.Project

class AsciiReveal implements Plugin<Project> {
    void apply(Project project) {

        project.plugins.with {
            apply com.github.jrubygradle.JRubyPlugin
            apply org.ysb33r.gradle.vfs.VfsPlugin
            apply org.asciidoctor.gradle.AsciidoctorPlugin
            apply name.mazgalov.vaadin.sass.compiler.VaadinSassCompiler
        }
        
        project.dependencies.with {
            gems 'rubygems:slim:3.0.6'
            gems 'rubygems:thread_safe:0.3.5'
            gems 'rubygems:asciidoctor-diagram:1.4.0'
        }
        
        project.extensions.create("asciiReveal", AsciiRevealExtension)

        project.task('themeResources', type: org.gradle.api.tasks.Copy) {
            from 'src/docs/sass' into 'build/download/reveal.js/css/theme'
        }
        
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

