package io.jstate.model.configuration;

import java.util.ArrayList;
import java.util.List;

public class ProcessDefinition {

    private String id;

    private String name;

    private List<String> processors = new ArrayList<>();

    private List<Transition> transitions;

    private List<State> states;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link String}
     */
    public String getId() {

        return id;
    }

    /**
     * Sets the value of the id property
     *
     * @param id
     *            allowed object is {@link String }
     * @return the {@link ProcessDefinition}
     */
    public ProcessDefinition setId(String id) {

        this.id = id;
        return this;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String}
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the value of the name property
     *
     * @param name
     *            allowed object is {@link String }
     * @return the {@link ProcessDefinition}
     */
    public ProcessDefinition setName(String name) {

        this.name = name;
        return this;
    }

    /**
     * Gets the value of the transitions property.
     *
     * @return possible object is {@link List< Transition>}
     */
    public List<Transition> getTransitions() {

        return transitions;
    }

    /**
     * Sets the value of the transitions property
     *
     * @param transitions
     *            allowed object is {@link List< Transition> }
     * @return the {@link ProcessDefinition}
     */
    public ProcessDefinition setTransitions(List<Transition> transitions) {

        this.transitions = transitions;
        return this;
    }

    /**
     * Gets the value of the states property.
     *
     * @return possible object is {@link List< State>}
     */
    public List<State> getStates() {

        return states;
    }

    /**
     * Sets the value of the states property
     *
     * @param states
     *            allowed object is {@link List< State> }
     * @return the {@link ProcessDefinition}
     */
    public ProcessDefinition setStates(List<State> states) {

        this.states = states;
        return this;
    }

    /**
     * Gets the value of the processors property.
     *
     * @return possible object is {@link List< String>}
     */
    public List<String> getProcessors() {

        return processors;
    }

    /**
     * Sets the value of the processors property
     *
     * @param processors
     *            allowed object is {@link List< String> }
     * @return the {@link ProcessDefinition}
     */
    public ProcessDefinition setProcessors(List<String> processors) {

        this.processors = processors;
        return this;
    }
}
