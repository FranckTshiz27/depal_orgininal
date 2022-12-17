/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Container;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IContainer {

    public Container saveOrUpdate(Container container);

    public int delete(int id);

    public Container getContainerById(int id);

    public List<Container> getContainers();
}
