package com.arangodb.spring.demo.services;


import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.spring.demo.entity.Order;
import com.arangodb.spring.demo.entity.Route;
import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.OrderRepository;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.arangodb.Protocol.HTTP_VPACK;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    RouteService routeService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order create(Order order) {
        String to = order.getTo().getId();
        String from = order.getFrom().getId();
        try {
            ArangoDB arango = new ArangoDB.Builder().user("root").password("root").useProtocol(HTTP_VPACK).build();
            ArangoDatabase db = arango.db("shipment");
            ArangoCursor<VPackSlice> cursor = db.query(
                    "FOR v, e IN OUTBOUND SHORTEST_PATH @from TO @to route FILTER  e._id != null RETURN e._id",
                    new MapBuilder().put("from", from).put("to", to).get(),
                    new AqlQueryOptions(),
                    VPackSlice.class
            );
            ArrayList<Route> routes = new ArrayList<>();
            for(; cursor.hasNext();) {
                VPackSlice obj = cursor.next();
                String id = obj.toString().substring(1, 6) + '/' + obj.toString().substring(8, obj.toString().length() - 1);
                routes.add(routeService.findById(id));
            }
            order.setPath(routes);

        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }

        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(String Id) {
        return orderRepository.findById(Id);
    }

    @Override
    public Boolean isOrderExist(Order order) {
        return orderRepository.exists(order.getId());
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(String Id) {
        orderRepository.delete(Id);
    }

    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
