package io.github.hotspacode.neeza.server.standalone.model;

import javax.persistence.*;

@Entity
@Table(name = "api_mock_data")
public class ApiMockData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer method_type;

    @Column(nullable = false)
    private String app_name;

    @Column(nullable = false)
    private String api_name;

    @Column(nullable = true)
    private String api_data;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMethod_type() {
        return method_type;
    }

    public void setMethod_type(Integer method_type) {
        this.method_type = method_type;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_data() {
        return api_data;
    }

    public void setApi_data(String api_data) {
        this.api_data = api_data;
    }
}
