# Spring Boot Application to show integration JPA entities with QueryDSL.
QueryDSL is small framework to generate typesafety sql.
**querydsl-jpa** is sort of wrapper under jql - build on EntityManager and allows to create 
typesafety queries rather than string-based jql.
Allows to return jpa Query object.
If configure to generate code from entities:
- entity -> QClass
- no objects for column, join tables
- thus pure support for many-to-many relations;
- dto/pojo conversion is quite pure (compare to jooq) - Tuple/to bean Projection
- Grouping/aggregation is quite cut as well - supports Map with count and groupBy only