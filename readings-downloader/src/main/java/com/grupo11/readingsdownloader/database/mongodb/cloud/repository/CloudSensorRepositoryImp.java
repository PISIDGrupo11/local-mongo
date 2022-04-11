package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class CloudSensorRepositoryImp implements  CloudSensorRepository{
    @Qualifier("cloudMongoTemplate")
    @Autowired
    MongoTemplate mongoTemplate;


    public void test(){
        Query q = new Query();
        q.addCriteria(Criteria.where("Medicao").is("24.61639494871795"));
        List<CloudSensor> l =mongoTemplate.find(q,CloudSensor.class);
        System.out.println(l);
    }
    @Override
    public List<CloudSensor> findCloudSensorByMedicaoEquals(String Medicao) {
        Query q = new Query();
        q.addCriteria(Criteria.where("Medicao").is(Medicao));
        List<CloudSensor> l =mongoTemplate.find(q,CloudSensor.class);
        System.out.println(l);
         return l;

    }

    @Override
    public Optional<CloudSensor> findCloudSensorsByDataEquals(String Data) {
        return Optional.empty();
    }

    @Override
    public <S extends CloudSensor> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CloudSensor> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CloudSensor> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<CloudSensor> findAll() {
        return null;
    }

    @Override
    public Iterable<CloudSensor> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(CloudSensor entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CloudSensor> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CloudSensor> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CloudSensor> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CloudSensor> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CloudSensor> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends CloudSensor> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CloudSensor> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CloudSensor> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CloudSensor> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CloudSensor> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CloudSensor> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CloudSensor, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
