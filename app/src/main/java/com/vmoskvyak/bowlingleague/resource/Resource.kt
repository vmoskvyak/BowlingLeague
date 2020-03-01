package com.vmoskvyak.bowlingleague.resource


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val message: String? = null) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> maxNumberOfPlayersReached(data: T?, msg: String? = null): Resource<T> {
            return Resource(Status.MAX_NUMBER_OF_PLAYERS_REACHED, data, msg)
        }

        fun <T> noPlayers(): Resource<T> {
            return Resource(Status.NO_PLAYERS, null)
        }
    }
}