package com.innovatrics.commons.vittap.model.dao;

public enum LevelOfAccess {
    ADMIN,
    SECRETARY,
    TEACHER,
    PUPIL,
    ANONYMOUS,  // not login user, can only view some information
}