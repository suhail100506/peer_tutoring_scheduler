package com.example.peertutoring.service.impl;

import com.example.peertutoring.entity.Dashboard;
import com.example.peertutoring.repository.DashboardRepository;
import com.example.peertutoring.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Dashboard getDashboardStatistics() {
        Dashboard dashboard = new Dashboard();
        dashboard.setTotalStudents(dashboardRepository.countTotalStudents());
        dashboard.setActiveTutors(dashboardRepository.countActiveTutors());
        dashboard.setUpcomingSessions(dashboardRepository.countUpcomingSessions());
        dashboard.setSatisfactionRate(dashboardRepository.getAverageSatisfactionRate());
        dashboard.setLastUpdated(java.time.LocalDateTime.now());
        return dashboard;
    }
}
