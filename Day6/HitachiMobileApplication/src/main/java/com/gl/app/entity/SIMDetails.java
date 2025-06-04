package com.gl.app.entity;

public record SIMDetails(
    long simId,
    long serviceNumber,
    long simNumber,
    String status,
    Long uniqueIdNumber
) {}
