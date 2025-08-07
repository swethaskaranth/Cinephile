package com.kaizencoder.cinephile.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import dagger.hilt.android.lifecycle.HiltViewModel
import org.junit.Test

class ArchitectureTest {

    @Test
    fun `use case classes must reside in domain layer`(){
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue { it.resideInPackage("..domain..") }
    }

    @Test
    fun `view models should be annotated with HiltViewModel`(){
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .assertTrue { it.hasAnnotationOf(HiltViewModel::class) }
    }

    @Test
    fun `architecture layers have dependencies correct`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                val presentationLayer = Layer("Presentation", "..presentation..")
                val domainLayer = Layer("Domain", "..domain..")
                val dataLayer = Layer("Data", "..data..")

                // Define layer dependencies
                presentationLayer.dependsOn(domainLayer)
                dataLayer.dependsOn(domainLayer)
                domainLayer.dependsOnNothing()
            }
    }

}
