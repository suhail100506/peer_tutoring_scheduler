package com.example.peertutoring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;

@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Transient
    private Long totalStudents;
    
    @Transient
    private Long activeTutors;
    
    @Transient
    private Long upcomingSessions;
    
    @Transient
    private Double satisfactionRate;
    
    private LocalDateTime lastUpdated;

    // Getters and Setters
    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getActiveTutors() {
        return activeTutors;
    }

    public void setActiveTutors(Long activeTutors) {
        this.activeTutors = activeTutors;
    }

    public Long getUpcomingSessions() {
        return upcomingSessions;
    }

    public void setUpcomingSessions(Long upcomingSessions) {
        this.upcomingSessions = upcomingSessions;
    }

    public Double getSatisfactionRate() {
        return satisfactionRate;
    }

    public void setSatisfactionRate(Double satisfactionRate) {
        this.satisfactionRate = satisfactionRate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Builder pattern for easy object creation
    public static class Builder {
        private final Dashboard dashboard;

        public Builder() {
            dashboard = new Dashboard();
        }

        public Builder withTotalStudents(Long totalStudents) {
            dashboard.setTotalStudents(totalStudents);
            return this;
        }

        public Builder withActiveTutors(Long activeTutors) {
            dashboard.setActiveTutors(activeTutors);
            return this;
        }

        public Builder withUpcomingSessions(Long upcomingSessions) {
            dashboard.setUpcomingSessions(upcomingSessions);
            return this;
        }

        public Builder withSatisfactionRate(Double satisfactionRate) {
            dashboard.setSatisfactionRate(satisfactionRate);
            return this;
        }

        public Builder withLastUpdated(LocalDateTime lastUpdated) {
            dashboard.setLastUpdated(lastUpdated);
            return this;
        }

        public Dashboard build() {
            return dashboard;
        }
    }
}
