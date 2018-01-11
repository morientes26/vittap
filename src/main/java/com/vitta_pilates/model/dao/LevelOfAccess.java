package com.vitta_pilates.model.dao;

public enum LevelOfAccess {
    ADMIN,
    SECRETARY,
    TEACHER,
    PUPIL,
    ANONYMOUS,  // not login user, can only view some information
}