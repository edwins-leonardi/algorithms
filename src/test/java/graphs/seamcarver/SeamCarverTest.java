package graphs.seamcarver;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import edu.princeton.cs.algs4.Picture;

public class SeamCarverTest {
    @Test
    public void removeVerticalSeam_7_x_3() {
        SeamCarver sc = new SeamCarver(new Picture("/seam-carving/7x3.png"));
        sc.removeVerticalSeam(sc.findVerticalSeam());
        Assert.assertThat(sc.width(), CoreMatchers.equalTo(6));
        Assert.assertThat(sc.height(), CoreMatchers.equalTo(3));
    }

    @Test
    public void removeHorizontalSeam_6_x_5() {
        SeamCarver sc = new SeamCarver(new Picture("/seam-carving/6x5.png"));
        sc.removeHorizontalSeam(sc.findHorizontalSeam());
        Assert.assertThat(sc.width(), CoreMatchers.equalTo(6));
        Assert.assertThat(sc.height(), CoreMatchers.equalTo(4));
    }

}
