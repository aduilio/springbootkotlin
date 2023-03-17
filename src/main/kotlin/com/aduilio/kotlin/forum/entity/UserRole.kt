package com.aduilio.kotlin.forum.entity

/**
 * Represents the role of the user.
 */
enum class UserRole(val value: String) {

    ADMIN("admin"), // can delete a topic
    STUDENT("student"), // can create a topic
    TEACHER("teacher"), // can update a topic
    NONE("none") // no permissions
}
