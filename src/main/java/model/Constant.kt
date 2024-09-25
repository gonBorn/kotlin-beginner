package model

object Constant {
    val JOBS_DATABASE: Map<JobId, Job> = mapOf(
        JobId(1) to Job(
            JobId(1),
            Company("Apple, Inc."),
            Role("Software Engineer"),
            Salary(70_000.00),
        ),
        JobId(2) to Job(
            JobId(2),
            Company("Microsoft"),
            Role("Software Engineer"),
            Salary(80_000.00),
        ),
        JobId(3) to Job(
            JobId(3),
            Company("Google"),
            Role("Software Engineer"),
            Salary(90_000.00),
        ),
    )
}