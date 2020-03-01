package com.vmoskvyak.bowlingleague.resource

/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    MAX_NUMBER_OF_PLAYERS_REACHED,
    NO_PLAYERS
}