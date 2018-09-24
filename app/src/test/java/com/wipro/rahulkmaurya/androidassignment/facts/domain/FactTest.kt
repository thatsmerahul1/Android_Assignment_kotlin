package com.wipro.rahulkmaurya.androidassignment.facts.domain

import com.wipro.rahulkmaurya.androidassignment.model.Fact
import org.junit.Test

/**
 * Class used to <insert description here>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
class FactTest {
    @Test
    fun testFact() {
        val fact = Fact("fact", "description of fact", "http://fact.org/logo.png")

        assert(fact.title == "fact")
        assert(fact.description == "description of fact")
        assert(fact.imageHref == "http://helloworld.com/logo.png")
    }

}