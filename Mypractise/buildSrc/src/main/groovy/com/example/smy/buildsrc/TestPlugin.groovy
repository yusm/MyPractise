package com.example.smy.buildsrc;


import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project>{

    @Override
    void apply(Project target) {
        project.task('pluginTest') {
            doLast {
                println 'Hello World'
            }
        }
    }
}