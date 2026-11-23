package com.mychess.gui;

import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import java.util.EnumMap;

import com.mychess.pieces.Piece;
import com.mychess.pieces.PieceColor;
import com.mychess.pieces.PieceType;

public class ImageLoader {
    private static final Map<PieceColor, Map<PieceType, ImageIcon>> pieceIcons = new EnumMap<>(PieceColor.class);

    static {
        for (PieceColor color : PieceColor.values()) {
            pieceIcons.put(color, new EnumMap<>(PieceType.class));

            for (PieceType type : PieceType.values()) {
                String path = "/assets/" + color.name().toLowerCase().charAt(0);
                path += type == PieceType.KNIGHT ? "n" : type.name().toLowerCase().charAt(0);
                path += ".png";
                URL imgUrl = ImageLoader.class.getResource(path);
                try {
                    BufferedImage image = ImageIO.read(imgUrl);
                    Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    pieceIcons.get(color).put(type, new ImageIcon(scaledImage));
                } catch (IOException e) {
                    System.out.println("Error accoured while fecthing images." + e.getMessage());
                }

            }
        }

    }
    public static ImageIcon getIcon(PieceColor color,PieceType type){
        return pieceIcons.get(color).get(type);
    }
    public static ImageIcon getPiece(Piece piece){
        if(piece== null){
            return null;
        }
        return getIcon(piece.getcolor(), piece.getType());
    }
}
