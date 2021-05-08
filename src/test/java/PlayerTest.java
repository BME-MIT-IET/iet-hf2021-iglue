import Field.Field;
import Field.IceBlock;
import Item.Item;
import Item.Tent;
import Player.Player;
import Player.Researcher;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PlayerTest {

    private Player player;
    private Field mockCurrentField;

    @BeforeEach
    void init() {
        player = new Researcher();
        mockCurrentField = mock(IceBlock.class);
    }


    @Test
    //Játékos tárgyat vesz fel (jelen esetben sátrat)
    void Player_PickUpItem() {
        //Arrange
        Item mockItem = mock(Tent.class);
        mockCurrentField.setItem(mockItem);
        player.setField(mockCurrentField);
        when(mockCurrentField.RemoveItem()).thenReturn(mockItem);

        //Act
        player.PickUpItem();

        //Assert
        verify(mockCurrentField, times(1)).RemoveItem();
        verify(mockItem, times(1)).setHolder(any());
    }

    @Test
    //Játékos lép, azaz mezőről mezőre lép
    void Player_Steps() {
        //Arrange
        Field mockTargetField = mock(IceBlock.class);
        player.setField(mockCurrentField);

        //Act
        player.Step(mockTargetField);

        //Assert
        verify(mockCurrentField, times(1)).Remove(player);
        verify(mockTargetField, times(1)).Accept(player);
    }
}
