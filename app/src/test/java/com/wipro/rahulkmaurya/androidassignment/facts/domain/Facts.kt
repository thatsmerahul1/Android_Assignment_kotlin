package com.wipro.rahulkmaurya.androidassignment.facts.domain

import com.wipro.rahulkmaurya.androidassignment.model.Fact
import com.wipro.rahulkmaurya.androidassignment.model.Facts
import org.junit.Test

/**
 * Class used to <insert description here>
 *
 * Created by rahul.k.maurya on 2018-09-24.
 */
class FactsTest {
    @Test
    fun emptyFactsTest() {
        val factList = mutableListOf<Fact>()
        val facts = Facts("test", factList)

        assert(facts.title == "test")
        assert(facts.rows!!.isEmpty())
    }

    @Test
    fun fullWithObjectFacts() {
        val factList = mutableListOf(Fact("fact", "description of fact", "http://helloworld.com/logo.png"))
        val facts = Facts("test", factList)

        assert(facts.title == "test")
        assert(facts.rows!!.size == 1)
        assert(facts.rows!![0].title == "fact")
    }

}