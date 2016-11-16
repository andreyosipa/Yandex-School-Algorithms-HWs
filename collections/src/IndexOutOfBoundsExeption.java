/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.collections;

/**
 *
 * @author Andrey
 */
class IndexOutOfBoundsExeption extends Exception {

    public IndexOutOfBoundsExeption() {
    }

    IndexOutOfBoundsExeption(String index_is_out_of_bounds) {
        System.out.print(index_is_out_of_bounds);
    }
    
}
