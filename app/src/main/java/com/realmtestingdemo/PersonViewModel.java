package com.realmtestingdemo;

import java.util.List;

/**
 * Created by on 09/05/17.
 * Purpose of this class is to
 */

class PersonViewModel {
    public String getAsString(final List<Person> persons) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Person person : persons) {
            stringBuilder.append(person.getId())
                    .append(Constants.StringValues.DOUBLE_SPACE)
                    .append(person.getName())
                    .append(Constants.StringValues.DOUBLE_SPACE)
                    .append(person.getAge())
                    .append(Constants.StringValues.NEW_LINE);
        }
        return stringBuilder.toString();
    }
}
